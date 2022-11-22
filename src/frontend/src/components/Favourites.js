import React, { useState, useEffect } from "react";
import * as apiCalls from '../api/apiCalls';

const Favourites = () => {

    const [data, setData] = useState([]);

    useEffect(() => {
        apiCalls.getFavourites().then((res) => {
            setData(res.data)
        });
    }, []);

    return (

        <div className="container">
            <h1>Favourites</h1>
            <p>A list of your favourite foods.</p>
            {data.map(favourite => {
                return (
                    <div key={favourite.id}>
                        <div>{favourite.name} {favourite.proteinPer100g} {favourite.fatsPer100g} {favourite.kcalPer100g}</div>
                        <hr />
                    </div>
                )
            })}
        </div>

    )
}



export default Favourites;