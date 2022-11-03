import axios from 'axios';

export function saveFood(
    name,
    weight,
    proteinPer100g,
    fatsPer100g,
    kcalPer100g,
    proteinTotal,
    fatsTotal,
    kcalTotal
) {
    const dto = makeDTO(name, weight, proteinPer100g, fatsPer100g, kcalPer100g, proteinTotal, fatsTotal, kcalTotal);
    return axios.post(basePath + `/add`, dto);
}

const basePath = 'http://localhost:8080/foods';


function makeDTO(name, weight, proteinPer100g, fatsPer100g, kcalPer100g, proteinTotal, fatsTotal, kcalTotal) {
    return {
        name: name,
        weight: weight,
        proteinPer100g: proteinPer100g,
        fatsPer100g: fatsPer100g,
        kcalPer100g: kcalPer100g,
        proteinTotal: proteinTotal,
        fatsTotal: fatsTotal,
        kcalTotal: kcalTotal
    };
}

