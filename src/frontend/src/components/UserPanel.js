import React from 'react';
import * as apiCalls from '../api/apiCalls';
import {saveFood} from "../api/apiCalls";

const options = [
    { id: 1, value: 'chicken', protein: 27, calories: 239 },
    { id: 2, value: 'eggs', protein: 13, calories: 155.1 },
    { id: 3, value: 'cheese piątnica semi-fat', protein: 16, calories: 485 },
    { id: 4, value: 'peanut butter', protein: 23.78, calories: 642 },
    { id: 5, value: 'oats', protein: 14, calories: 418 },
    { id: 6, value: 'walnut', protein: 15, calories: 654.4 },
    { id: 7, value: 'dark bread', protein: 8.5, calories: 218 },
    { id: 8, value: 'protein bar Olymp 64 g', protein: 31, calories: 369 },
    { id: 9, value: 'WPI 90 Olymp', protein: 90, calories: 373 },
    { id: 10, value: 'wheat noodles, cooked', protein: 5.15, calories: 131 }
]

class UserPanel extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            weight: 0,
            protein: 0,
            totalProtein: 0,
            totalKcal: 0,
            calories: 0,
            id: 0,
            name: '',
            foods: []
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);

    }

    addObjectToArray = e => {
        e.preventDefault();
        let proteinPerFood = (this.state.weight * this.state.protein) / 100
        let caloriesPerFood = (this.state.weight * this.state.calories) / 100
        this.setState({
            foods: [
                ...this.state.foods,
                { id: this.state.id + 1, name: this.state.name, protein: proteinPerFood, calories: caloriesPerFood }
            ],
            name: '',
            protein: 0,
            weight: 0,
            id: this.state.id + 1,
            totalProtein: this.state.totalProtein + proteinPerFood,
            totalKcal: this.state.totalKcal + caloriesPerFood
        })

        saveFood();
    };

    saveFood = () => {
        apiCalls.saveFood(
            this.state.name,
            this.state.weight,
            this.state.proteinPer100g,
            this.state.fatsPer100g,
            this.state.kcalPer100g,
            this.state.proteinTotal,
            this.state.fatsTotal,
            this.state.kcalTotal

        ).then(response => {
            console.log(response.data)
        })
    };

    removeObject = val => {
        let obj = this.state.foods.find(o => o.id === val);
        this.setState({
            foods: this.state.foods.filter(obj => {
                return obj.id !== val;
            }),
            totalProtein: this.state.totalProtein - obj.protein,
            totalKcal: this.state.totalKcal - obj.calories

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
                protein: obj.protein,
                calories: obj.calories
            });
        }
    }

    render() {
        return <>
            <h3>Total calories {Math.round(this.state.totalKcal * 100) / 100}kcal</h3>
            <h3>Total protein {Math.round(this.state.totalProtein * 100) / 100}g</h3>

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
                name="protein"
                value={this.state.protein}
                onChange={this.handleChange} />
            <label>calories/100g</label>
            <input
                name="calories"
                value={this.state.calories}
                onChange={this.handleChange} />
            {this.state.foods.map(food => {
                return (
                    <div key={food.id}>
                        <p>food: {food.name}, protein: {Math.round(food.protein * 100) / 100}g, calories: {Math.round(food.calories * 100) / 100}kcal</p>
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













