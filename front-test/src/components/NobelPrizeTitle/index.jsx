import React from "react";

import Container from '@material-ui/core/Container';

const NobelPrizeTitle = () => {

    return (
        <div>
            <Container maxWidth="sm">
                <h1>Nobel prize finder:</h1>
                <p> Searching in: {process.env.REACT_APP_BACK_API_URL} service</p>
            </Container>
        </div>
    );

}

export default NobelPrizeTitle;