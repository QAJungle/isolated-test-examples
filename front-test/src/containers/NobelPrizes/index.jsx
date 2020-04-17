import React, { useState, useEffect } from 'react';

import NobelPrizeSearch from './../../components/NobelPrizeSearch';
import NobelPrizeList from './../../components/NobelPrizeList';
import NobelPrizeTitle from './../../components/NobelPrizeTitle';

import { findByCategoryAndYears } from '../../services/NobelPrizeService';

import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';

const useStyles = makeStyles(theme => ({
    gridItem: {
        marginTop: "40px"
      }
  }));

const NobelPrizes = () => {

    const classes = useStyles();

    const [prizes, setPrizes] = useState();

    const handleSearchSubmit = data => {
        const { category, from, to } = data;
        const payload = {
            category,
            from,
            to
        }
  
        findByCategoryAndYears({ payload })
        .then(
          prizes  => {
            setPrizes(prizes)
          });
      };

    return (
        <div>
            <Grid container className={classes.gridContainer}>
                <Grid item xs={12} sm={12} md={12} className={classes.gridItem}>
                    <NobelPrizeTitle/>
                </Grid>
                <Grid item xs={12} sm={12} md={12} className={classes.gridItem}>
                    <NobelPrizeSearch handleSubmitForm = { handleSearchSubmit }/>
                </Grid>
                <Grid item xs={12} sm={12} md={12} className={classes.gridItem}>
                    <NobelPrizeList prizes = { prizes }/>
                </Grid>
            </Grid>
        </div>
    );
}

export default NobelPrizes;