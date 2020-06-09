//import { axios } from 'axios';
const fetch = require("node-fetch");

export const findByCategoryAndYears = async ({url, payload}) => {
    const response = await fetch(`${url}/nobel_prizes/v1?category=${payload.category}&from=${payload.from}&to=${payload.to}`);
    const prizes = await response.json();

    return prizes;
  };