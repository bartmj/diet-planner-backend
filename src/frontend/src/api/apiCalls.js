import axios from 'axios';

// this.state.name,
//     this.state.weight,
//     this.state.proteinPer100g,
//     this.state.fatsPer100g,
//     this.state.kcalPer100g,
//     this.state.proteinTotal,
//     this.state.fatsTotal,
//     this.state.kcalTotal

export const saveFood = (
    id,
    name,
    weight,
    proteinPer100g,
    fatsPer100g,
    kcalPer100g,
    proteinTotal,
    fatsTotal,
    kcalTotal
) => {
    const dto = makeDTO(id, name, weight, proteinPer100g, fatsPer100g, kcalPer100g, proteinTotal, fatsTotal, kcalTotal);
    return axios.post(basePath + `/add`, dto);
}

const basePath = 'http://localhost:8080/foods';


function makeDTO(id, name, weight, proteinPer100g, fatsPer100g, kcalPer100g, proteinTotal, fatsTotal, kcalTotal) {
    return {
        id: id,
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

