import React, { useState, useEffect } from "react";
import * as apiCalls from '../api/apiCalls';

const Favourites = () => {

    const [data, setData] = useState([]);

    useEffect(() => {
        apiCalls.getFavourites().then((res) => {
            setData(res.data)
        });
    }, data);

    return (
        <ul>
            {data.map(favourite => {
                return (
                    <li>{favourite.name} {favourite.proteinPer100g} {favourite.fatsPer100g} {favourite.kcalPer100g}</li>
                )
            })}
        </ul>
    )
}



export default Favourites;