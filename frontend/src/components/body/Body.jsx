import React from "react";
import PropTypes from "prop-types";
import {
  AppBar,
  Tabs,
  Tab,
  Typography,
  Box,
  TextField,
  Button,
} from "@mui/material";

import { makeStyles } from "@mui/styles";

import Homepage from "./Homepage";
import AddData from "./AddData";
import SearchResult from "./SearchResult";
import AnalyticsView from "./AnalyticsView";

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 4 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    boxSizing: "border-box",
    padding: "10px",
    backgroundColor: "#666666",

    "& .MuiPaper-root": {
      boxShadow: "none",
    },
    "& .MuiTabs-root": {
      position: "relative",
      backgroundColor: "#666666",
    },
    "& .MuiTabs-indicator": {
      backgroundColor: "#fff",
    },
    "& .css-1h9z7r5-MuiButtonBase-root-MuiTab-root": {
      color: "#fff",
      fontWeight: 600,
    },
    "& .css-1h9z7r5-MuiButtonBase-root-MuiTab-root.Mui-selected": {
      color: "#fff",
    },
    "& .custom-header-component": {
      display: "flex",
      gap: "10px",
      position: "absolute",
      right: "10px",
      zIndex: 5,
    },
    "& .MuiButton-root": {
      color: "#fff",
      fontWeight: 600,
      backgroundColor: "#fc7500",
      boxShadow: "none",
      marginRight: "10px",
      transition: "0.3s ease",

      "&:hover": {
        color: "#fff !important",
        backgroundColor: "#0000CD !important",
        boxShadow: "none",
      },
    },
    "& .MuiFilledInput-root": {
      backgroundColor: "#fff",

      "&:hover": {
        backgroundColor: "#fff",
      },

      "&:focused": {
        backgroundColor: "#fff",
        color: "#666666",
      },

      "&:before": {
        borderBottom: "none",
      },
      "&:after": {
        borderBottom: "none",
      },
    },
  },
  button: {
    color: "#666666 !important",
    backgroundColor: "#8fd167 !important",
  },
}));

export default function BasicTabs() {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className={classes.root}>
      <div className="custom-header-component">
        <TextField
          id="search-customer-order-id"
          label="Search Customer Order ID"
          variant="filled"
          fullWidth
        />
        <Button variant="contained" color="primary" className={classes.button}>
          Advanced search
        </Button>
      </div>
      <AppBar position="static">
        <Tabs
          value={value}
          onChange={handleChange}
          aria-label="menu-view tabs example"
        >
          <Tab label="HOMEPAGE" {...a11yProps(0)} />
          <Tab label="ADD DATA" {...a11yProps(1)} />
          <Tab label="SEARCH RESULTS" {...a11yProps(2)} />
          <Tab label="ANALYTICS VIEW" {...a11yProps(3)} />
        </Tabs>
      </AppBar>
      <TabPanel value={value} index={0}>
        <Homepage />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <AddData />
      </TabPanel>
      <TabPanel value={value} index={2}>
        <SearchResult />
      </TabPanel>
      <TabPanel value={value} index={3}>
        <AnalyticsView />
      </TabPanel>
    </div>
  );
}
