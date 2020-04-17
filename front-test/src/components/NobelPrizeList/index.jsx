import React from "react";

import { makeStyles } from '@material-ui/core/styles';
import MaterialTable from "material-table";
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';

const useStyles = makeStyles(theme => ({
    gridItem: {
        marginBottom: "40px"
      },
    container: {
        display: 'flex',
        flexWrap: 'wrap',
      }
  }));

const NobelPrizeList = ({ prizes }) => {

    const classes = useStyles();

    return (
        <div>
            <Container maxWidth="md">
                <Grid container className={classes.gridContainer}>
                    <Grid item xs={12} sm={12} md={12} className={classes.gridItem}>
                        <p data-test="result-text">
                            Nobel prizes of <strong>{prizes ? prizes.category : ""}</strong> category from <strong>{prizes ? prizes.from : ""}</strong> to <strong>{prizes ? prizes.to : ""}</strong> year.
                        </p>    
                    </Grid>
                    <Grid item xs={12} sm={12} md={12} className={classes.gridItem}>
                        <MaterialTable
                            options={{
                                search: false,
                                sorting: false
                            }}
                            columns={[
                            { title: "Year", 
                                field: "year",
                                render: rowData => <p data-test="result-list-year">{rowData.year}</p>
                            },
                            { title: "Name", 
                                field: "firstname",
                                render: rowData => <p data-test="result-list-firstname">{rowData.firstname}</p>
                            },
                            { title: "Surname", 
                                field: "surname",
                                render: rowData => <p data-test="result-list-surname">{rowData.surname}</p>
                            }
                            ]}
                            data={ prizes ? prizes.prizes : [] }
                            title="Nobel prize list"
                        />
                    </Grid>
                </Grid>
            </Container>
        </div>
    );
}

export default NobelPrizeList;