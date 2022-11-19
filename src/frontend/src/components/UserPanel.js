import React from 'react';
import * as apiCalls from '../api/apiCalls';
import store from '../store';

const options = [
    { id: 1, value: 'chicken', proteinPer100g: 27, kcalPer100g: 239, fatsPer100g: 1 },
    { id: 2, value: 'eggs', proteinPer100g: 13, kcalPer100g: 155.1, fatsPer100g: 1 },
    { id: 3, value: 'cheese piątnica semi-fat', proteinPer100g: 16, kcalPer100g: 485, fatsPer100g: 1 },
    { id: 4, value: 'peanut butter', proteinPer100g: 23.78, kcalPer100g: 642, fatsPer100g: 1 },
    { id: 5, value: 'oats', proteinPer100g: 14, kcalPer100g: 418, fatsPer100g: 1 },
    { id: 6, value: 'walnut', proteinPer100g: 15, kcalPer100g: 654.4, fatsPer100g: 1 },
    { id: 7, value: 'dark bread', proteinPer100g: 8.5, kcalPer100g: 218, fatsPer100g: 1 },
    { id: 8, value: 'protein bar Olymp 64 g', proteinPer100g: 31, kcalPer100g: 369, fatsPer100g: 1 },
    { id: 9, value: 'WPI 90 Olymp', proteinPer100g: 90, kcalPer100g: 373, fatsPer100g: 1 },
    { id: 10, value: 'wheat noodles, cooked', proteinPer100g: 5.15, kcalPer100g: 131, fatsPer100g: 1 }
]

class UserPanel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            weight: 0,
            proteinPer100g: 0,
            fatsPer100g: 0,
            kcalPer100g: 0,
            totalDayProtein: 0,
            totalDayFats: 0,
            totalDayKcal: 0,
            foods: [],
            ifFavourite: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleCheckBoxChange = this.handleCheckBoxChange.bind(this);

    }

    state = store.getState();

    componentDidMount() {
        this.loadFoods();
        console.log()
    }

    addObjectToArray = e => {
        e.preventDefault();

        let proteinPerFood = (this.state.weight * this.state.proteinPer100g) / 100
        let kcalPerFood = (this.state.weight * this.state.kcalPer100g) / 100
        let fatsPerFood = (this.state.weight * this.state.fatsPer100g) / 100

        let dtoObj = {
            id: null,
            name: this.state.name,
            weight: this.state.weight,
            proteinPer100g: this.state.proteinPer100g,
            fatsPer100g: this.state.fatsPer100g,
            kcalPer100g: this.state.kcalPer100g,
            proteinTotal: proteinPerFood,
            fatsTotal: fatsPerFood,
            kcalTotal: kcalPerFood,
            userId: store.getState().auth.user.id,
            ifFavourite: this.state.ifFavourite
        }

        apiCalls.saveFood(dtoObj).then(response => {
            let idFromDb = response.data

            this.setState({
                foods: [
                    ...this.state.foods,
                    { id: idFromDb, name: this.state.name, proteinTotal: proteinPerFood, kcalTotal: kcalPerFood, totalFats: fatsPerFood }
                ],
                name: '',
                weight: 0,
                proteinPer100g: 0,
                fatsPer100g: 0,
                kcalPer100g: 0,
                totalDayProtein: this.state.totalDayProtein + proteinPerFood,
                totalDayFats: this.state.totalDayFats + fatsPerFood,
                totalDayKcal: this.state.totalDayKcal + kcalPerFood
            })
        })
    };


    removeObject = val => {
        let obj = this.state.foods.find(o => o.id === val);
        this.setState({
            foods: this.state.foods.filter(obj => {
                return obj.id !== val;
            }),
            totalDayProtein: this.state.totalDayProtein - obj.proteinTotal,
            totalDayKcal: this.state.totalDayKcal - obj.kcalTotal
        })
        apiCalls.remove(val)
    }

    handleChange(e) {
        const value = e.target.value
        this.setState({
            [e.target.name]: value
        });
    }

    loadFoods = () => {
        apiCalls.getAll().then(response => {
            this.setState({
                foods: response.data
            })
            let totalDayProtein = 0, totalDayFats = 0, totalDayKcal = 0
            for (const element of response.data) {
                totalDayProtein = totalDayProtein + element.proteinTotal
                totalDayFats = totalDayFats + element.fatsTotal
                totalDayKcal = totalDayKcal + element.kcalTotal
            }
            this.setState({
                totalDayProtein: totalDayProtein,
                totalDayFats: totalDayFats,
                totalDayKcal: totalDayKcal
            })
        })
    }

    handleNameChange(e) {
        let n = e.target.value
        this.setState({
            name: n
        })
        let obj = options.find(o => o.value.toLowerCase() === n.toLowerCase());
        if (obj) {
            this.setState({
                proteinPer100g: obj.proteinPer100g,
                kcalPer100g: obj.kcalPer100g,
                fatsPer100g: obj.fatsPer100g
            });
        }
    }

    handleCheckBoxChange() {
        let bool = this.state.ifFavourite === false
        this.setState({
            ifFavourite: bool
        })
    }

    render() {
        return <div className="container">
            <h1>Macro calculator</h1>

            <div className="form-group">
                <label>food: </label>
                <input
                    id="multiselect"
                    type="text"
                    name=""
                    list="productName"
                    onChange={this.handleNameChange}
                    value={this.state.name}
                />

                <datalist id="productName">
                    {options.map(option => {
                        return (
                            <option key={option.id} value={option.value}>{option.value}</option>
                        );
                    })}
                </datalist>
            </div>

            <div className="form-group">
                <label>weight (grams):</label>
                <input
                    name="weight"
                    value={this.state.weight}
                    onChange={this.handleChange} />
            </div>

            <div className="form-group">
                <label>protein/100g: </label>
                <input
                    name="proteinPer100g"
                    value={this.state.proteinPer100g}
                    onChange={this.handleChange} />
            </div>

            <div className="form-group">
                <label>fats/100g: </label>
                <input
                    name="fatsPer100g"
                    value={this.state.fatsPer100g}
                    onChange={this.handleChange} />
            </div>

            <div className="form-group">
                <label>calories/100g: </label>
                <input
                    name="kcalPer100g"
                    value={this.state.kcalPer100g}
                    onChange={this.handleChange} />
            </div>

            <div className="flex-01">
                <button
                    className="add-button"
                    onClick={this.addObjectToArray}>
                    Add food
                </button>

                <div>
                    <input type="checkbox" id="ifFavourite" name="ifFavourite" value="" onChange={this.handleCheckBoxChange}/>
                    <label htmlFor="ifFavourite">Add to favourites</label>
                </div>
            </div>

            <h3>Today:</h3>
            <h3>Total calories {Math.round(this.state.totalDayKcal * 100) / 100}kcal</h3>
            <h3>Total protein {Math.round(this.state.totalDayProtein * 100) / 100}g</h3>

            {this.state.foods.map(food => {
                return (
                    <div key={food.id} className="grid-1-1">
                        <div>
                            <p>food: {food.name},
                                protein: {Math.round(food.proteinTotal * 100) / 100}g, calories: {Math.round(food.kcalTotal * 100) / 100}kcal</p>
                        </div>
                        <div className="x-btn">
                            <button
                                onClick={() => this.removeObject(food.id)}
                                type="button">
                                x
                            </button>
                        </div>
                        <hr />
                    </div>
                );
            })}
        </div>
    }
}

export default UserPanel;













