import React, { useState, useEffect } from "react";
import * as apiCalls from '../api/apiCalls';

const Favourites = () => {

    const [data, setData] = useState([]);

    useEffect(() => {
        apiCalls.getFavourites().then((res) => {
            console.log(res)
            setData(res.data)
        });
    }, []);

    return (

        <div className="container">
            <h1>Favourites</h1>
            <h3>A list of your favourite foods:</h3>
            {data.map(favourite => {
                return (
                    <div key={favourite.id}>
                        <p><strong>{favourite.name}</strong></p>
                        <p>
                            kcal: {favourite.kcalPer100g},
                            protein: {favourite.proteinPer100g}g,
                            fats: {favourite.fatsPer100g},
                            carbs: {favourite.carbsPer100g}
                        </p>
                        <hr />
                    </div>
                )
            })}
        </div>

    )
}



export default Favourites;