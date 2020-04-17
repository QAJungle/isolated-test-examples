export const findByCategoryAndYears = async ({payload}) => {
    const response = await fetch(`${process.env.REACT_APP_BACK_API_URL}/nobel_prizes/v1/?category=${payload.category}&from=${payload.from}&to=${payload.to}`);
    const prizes = await response.json();
    return prizes;
  };