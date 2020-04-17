import React from "react";
import useForm from "react-hook-form";

import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';

const useStyles = makeStyles(theme => ({
    gridItem: {
        marginBottom: "10px"
      },
    button: {
      margin: theme.spacing(1),
    },
    container: {
        display: 'flex',
        flexWrap: 'wrap',
      },
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: 200,
    },
  }));

const NobelPrizeSearch = ({ handleSubmitForm }) => {

    const classes = useStyles();

    const { register, handleSubmit } = useForm({
        defaultValues: {
            category: '', 
            from: '', 
            to: ''
        },
    });

    return (
        <div>
            <Container maxWidth="sm">
                <form onSubmit={handleSubmit(handleSubmitForm)}>
                    <Grid container className={classes.gridContainer}>
                        <Grid item className={classes.gridItem}>
                            <TextField
                                id="category"
                                name="category"
                                label="Category"
                                margin="normal"
                                inputRef={ register }
                                inputProps= {{
                                    "data-test": "search-category"
                                }}
                                className={classes.textField}
                                />
                        </Grid>
                        <Grid item className={classes.gridItem}>
                            <TextField
                                id="from"
                                name="from"
                                label="Date from:"
                                margin="normal"
                                inputRef={ register }
                                inputProps= {{
                                    "data-test": "search-from"
                                }}
                                className={classes.textField}
                                />
                                <TextField
                                id="to"
                                name="to"
                                label="Date to"
                                margin="normal"
                                inputRef={ register }
                                inputProps= {{
                                    "data-test": "search-to"
                                }}
                                className={classes.textField}
                                />
                        </Grid>
                        <Grid item className={classes.gridItem}>
                            <Button 
                            variant="contained" 
                            color="primary" 
                            type="submit" 
                            className={classes.button}
                            data-test="search-button"
                            >
                                Search
                            </Button>
                        </Grid>
                    </Grid>        
                </form>
            </Container>
        </div>
    );

}

export default NobelPrizeSearch;