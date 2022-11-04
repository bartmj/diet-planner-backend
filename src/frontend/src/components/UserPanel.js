import React from 'react';
import * as apiCalls from '../api/apiCalls';

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
            id: 0,
            name: '',
            weight: 0,
            proteinPer100g: 0,
            fatsPer100g: 0,
            kcalPer100g: 0,
            totalProtein: 0,
            totalFats: 0,
            totalKcal: 0,
            totalDayProtein: 0,
            totalDayFats: 0,
            totalDayKcal: 0,
            foods: []
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);

    }

    addObjectToArray = e => {
        e.preventDefault();
        let proteinPerFood = (this.state.weight * this.state.proteinPer100g) / 100
        let kcalPerFood = (this.state.weight * this.state.kcalPer100g) / 100
        let fatsPerFood = (this.state.weight * this.state.fatsPer100g) / 100

        this.setState({
            foods: [
                ...this.state.foods,
                { id: this.state.id + 1, name: this.state.name, totalProtein: proteinPerFood, totalKcal: kcalPerFood, totalFats: fatsPerFood }
            ],
            id: this.state.id + 1,
            name: '',
            totalProtein: proteinPerFood,
            totalKcal: kcalPerFood,
            totalFats: fatsPerFood,
            totalDayProtein: this.state.totalDayProtein + proteinPerFood,
            totalDayFats: this.state.totalDayFats + fatsPerFood,
            totalDayKcal: this.state.totalDayKcal + kcalPerFood
        })

        apiCalls.saveFood(
            this.state.id,
            this.state.name,
            this.state.weight,
            this.state.proteinPer100g,
            this.state.fatsPer100g,
            this.state.kcalPer100g,
            proteinPerFood,
            fatsPerFood,
            kcalPerFood
        )
        //     .then(response => {
        //     console.log(response.data)
        // })

        this.setState({
            weight: 0,
            proteinPer100g: 0,
            fatsPer100g: 0,
            kcalPer100g: 0,
        })
    };


    removeObject = val => {
        let obj = this.state.foods.find(o => o.id === val);
        this.setState({
            foods: this.state.foods.filter(obj => {
                return obj.id !== val;
            }),
            totalDayProtein: this.state.totalDayProtein - obj.totalProtein,
            totalDayKcal: this.state.totalDayKcal - obj.totalKcal

        })
    }

    handleChange(e) {
        const value = e.target.value
        this.setState({
            [e.target.name]: value
        });
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

    render() {
        return <>
            <h3>Total calories {Math.round(this.state.totalDayKcal * 100) / 100}kcal</h3>
            <h3>Total protein {Math.round(this.state.totalDayProtein * 100) / 100}g</h3>

            <label>food:</label>
            <input
                id="multiselect"
                type="text"
                name=""
                list="productName"
                onChange={this.handleNameChange}
                value={this.state.name} />
            <datalist id="productName">
                {options.map(option => {
                    return (
                        <option key={option.id} value={option.value}>{option.value}</option>
                    );
                })}
            </datalist>
            <label>weight (grams):</label>
            <input
                name="weight"
                value={this.state.weight}
                onChange={this.handleChange} />
            <label>protein/100g:</label>
            <input
                name="proteinPer100g"
                value={this.state.proteinPer100g}
                onChange={this.handleChange} />
            <label>fats/100g</label>
            <input
                name="fatsPer100g"
                value={this.state.fatsPer100g}
                onChange={this.handleChange} />
            <label>calories/100g</label>
            <input
                name="caloriesPer100g"
                value={this.state.kcalPer100g}
                onChange={this.handleChange} />
            {this.state.foods.map(food => {
                return (
                    <div key={food.id}>
                        <p>food: {food.name}, protein: {Math.round(food.totalProtein * 100) / 100}g, calories: {Math.round(food.totalKcal * 100) / 100}kcal</p>
                        <button
                            onClick={() => this.removeObject(food.id)}
                            type="button">x</button>
                        <hr />
                    </div>
                );
            })}

            <button
                className="add-button"
                onClick={this.addObjectToArray}>
                Add food
            </button>
        </>
    }
}

export default UserPanel;













