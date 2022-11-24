import React from 'react';
import * as apiCalls from '../api/apiCalls';
import store from '../store';

class UserPanel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            weight: 0,
            proteinPer100g: 0,
            fatsPer100g: 0,
            carbsPer100g: 0,
            kcalPer100g: 0,
            totalDayProtein: 0,
            totalDayFats: 0,
            totalDayCarbs: 0,
            totalDayKcal: 0,
            foods: [],
            ifFavourite: false,
            options: []
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleCheckBoxChange = this.handleCheckBoxChange.bind(this);
    }

    state = store.getState();

    componentDidMount() {
        this.loadFoods();
        this.loadFavourites();
    }

    loadFavourites() {
        apiCalls.getFavourites().then((res) => {
            this.setState({
                options: res.data
            })
        })
    }

    addObjectToArray = e => {
        e.preventDefault();

        let proteinPerFood = (this.state.weight * this.state.proteinPer100g) / 100
        let kcalPerFood = (this.state.weight * this.state.kcalPer100g) / 100
        let fatsPerFood = (this.state.weight * this.state.fatsPer100g) / 100
        let carbsPerFood = (this.state.weight * this.state.carbsPer100g) / 100

        let dtoObj = {
            id: null,
            name: this.state.name,
            weight: this.state.weight,
            proteinPer100g: this.state.proteinPer100g,
            fatsPer100g: this.state.fatsPer100g,
            kcalPer100g: this.state.kcalPer100g,
            carbsPer100g: this.state.carbsPer100g,
            proteinTotal: proteinPerFood,
            fatsTotal: fatsPerFood,
            kcalTotal: kcalPerFood,
            carbsTotal: carbsPerFood,
            ifFavourite: this.state.ifFavourite
        }

        apiCalls.saveFood(dtoObj).then(response => {
            const {
                data: { id }
            } = response

            this.setState({
                foods: [
                    ...this.state.foods,
                    { id: id, name: this.state.name,
                        proteinTotal: proteinPerFood,
                        kcalTotal: kcalPerFood,
                        fatsTotal: fatsPerFood,
                        carbsTotal: carbsPerFood}
                ],
                name: '',
                weight: 0,
                proteinPer100g: 0,
                fatsPer100g: 0,
                kcalPer100g: 0,
                totalDayProtein: this.state.totalDayProtein + proteinPerFood,
                totalDayFats: this.state.totalDayFats + fatsPerFood,
                totalDayKcal: this.state.totalDayKcal + kcalPerFood,
                totalDayCarbs: this.state.totalDayCarbs + carbsPerFood,
                ifFavourite: false
            })
        })
        this.loadFavourites();
    };

    removeObject = val => {
        let obj = this.state.foods.find(o => o.id === val);
        this.setState({
            foods: this.state.foods.filter(obj => {
                return obj.id !== val;
            }),
            totalDayProtein: this.state.totalDayProtein - obj.proteinTotal,
            totalDayKcal: this.state.totalDayKcal - obj.kcalTotal,
            totalDayFats: this.state.totalDayFats - obj.fatsTotal,
            totalDayCarbs: this.state.totalDayCarbs - obj.carbsTotal

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
            let totalDayProtein = 0, totalDayFats = 0, totalDayKcal = 0, totalDayCarbs = 0
            for (const element of response.data) {
                totalDayProtein = totalDayProtein + element.proteinTotal
                totalDayFats = totalDayFats + element.fatsTotal
                totalDayKcal = totalDayKcal + element.kcalTotal
                totalDayCarbs = totalDayCarbs + element.carbsTotal
            }
            this.setState({
                totalDayProtein: totalDayProtein,
                totalDayFats: totalDayFats,
                totalDayKcal: totalDayKcal,
                totalDayCarbs: totalDayCarbs
            })
        })
    }

    handleNameChange(e) {
        let n = e.target.value
        this.setState({
            name: n
        })
        let obj = this.state.options.find(o => o.name.toLowerCase() === n.toLowerCase());
        if (obj) {
            this.setState({
                proteinPer100g: obj.proteinPer100g,
                kcalPer100g: obj.kcalPer100g,
                fatsPer100g: obj.fatsPer100g,
                carbsPer100g: obj.carbsPer100g
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
                    {this.state.options.map(option => {
                        return (
                            <option key={option.id} value={option.name}>{option.name}</option>
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
                <label>carbohydrates/100g: </label>
                <input
                    name="carbsPer100g"
                    value={this.state.carbsPer100g}
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
                    <input
                        type="checkbox"
                        id="ifFavourite"
                        name="ifFavourite"
                        checked={this.state.ifFavourite ? 'checked' : ''}
                        onChange={this.handleCheckBoxChange}/>
                    <label htmlFor="ifFavourite">Add to favourites</label>
                </div>
            </div>

            <h4>Today:</h4>
            <h4>Total calories {Math.round(this.state.totalDayKcal * 100) / 100}kcal</h4>
            <h4>Total protein {Math.round(this.state.totalDayProtein * 100) / 100}g</h4>
            <h4>Total fats {Math.round(this.state.totalDayFats * 100) / 100}g</h4>
            <h4>Total carbohydrates {Math.round(this.state.totalDayCarbs * 100) / 100}g</h4>

            {this.state.foods.map(food => {
                return (
                    <div key={food.id}>
                        <div className="flex">
                            <div>
                                <p>{food.name},
                                    protein: {Math.round(food.proteinTotal * 100) / 100}g,
                                    calories: {Math.round(food.kcalTotal * 100) / 100}kcal,
                                    fats: {Math.round(food.fatsTotal * 100) / 100}g,
                                    carbohydrates: {Math.round(food.carbsTotal * 100) / 100}g
                                </p>
                            </div>
                            <div className="x-btn">
                                <button
                                    onClick={() => this.removeObject(food.id)}
                                    type="button">
                                    x
                                </button>
                            </div>
                        </div>
                        <hr />
                    </div>
                );
            })}
        </div>
    }
}

export default UserPanel;













