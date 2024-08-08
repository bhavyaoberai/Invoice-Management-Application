import { React, useState, useEffect } from "react";
import axios from "axios";
import { DataGrid, GridFooterContainer, GridFooter } from "@mui/x-data-grid";
import { Button } from "@mui/material";
import { makeStyles } from "@mui/styles";

let columns = [
  {
    field: "slNo",
    headerName: "Sl No",
    width: 100,
  },
  {
    field: "customerOrderId",
    headerName: "CUSTOMER ORDER ID",
    width: 240,
  },
  {
    field: "salesOrg",
    headerName: "SALES ORG",
    width: 160,
  },
  {
    field: "distributionChannel",
    headerName: "DISTRIBUTION CHANNEL",
    width: 320,
  },
  {
    field: "companyCode",
    headerName: "COMPANY CODE",
    width: 180,
  },
  {
    field: "orderCreationDate",
    headerName: "ORDER CREATION DATE",
    width: 280,
  },
  {
    field: "orderAmount",
    headerName: "ORDER AMOUNT",
    width: 180,
  },
  {
    field: "orderCurrency",
    headerName: "ORDER CURRENCY",
    width: 200,
  },
  {
    field: "customerNumber",
    headerName: "CUSTOMER NUMBER",
    width: 220,
  },
  {
    field: "amountInUSD",
    headerName: "AMOUNT IN USD",
    width: 220,
  },
];

columns = columns.map((col) => ({
  ...col,
  editable: false,
  sortable: false,
}));

function CustomFooter() {
  return (
    <GridFooterContainer>
      <div>
        <Button varient="contained" color="primary">
          Refresh
        </Button>
        <Button
          varient="contained"
          color="primary"
          disabled={selectedRowsData && selectedRowsData.length !== 1 && true}
        >
          Edit
        </Button>
        <Button
          varient="contained"
          color="primary"
          disabled={selectedRowsData && selectedRowsData.length < 1 && true}
        >
          Delete
        </Button>
        <Button
          varient="contained"
          color="primary"
          disabled={selectedRowsData && selectedRowsData.length !== 1 && true}
        >
          Predict
        </Button>
      </div>

      <GridFooter />
    </GridFooterContainer>
  );
}

const useStyles = makeStyles({
  root: {
    backgroundColor: "#666666",
    fontFamily: "Roboto",

    "& .MuiDataGrid-columnSeparator": {
      display: "none",
    },
    "& .MuiDataGrid-root": {
      border: "none",
    },
    "& .MuiDataGrid-footerContainer": {
      justifyContent: "space-between",
      color: "white",
    },
    "& .MuiTablePagination-toolbar": {
      color: "white",
    },
    "& .MuiDataGrid-row--lastVisible .MuiDataGrid-cell": {
      border: "none",
    },
    "& .MuiCheckbox-root": {
      color: "white",
    },
    "& .MuiDataGrid-cell": {
      color: "#fff",
      fontWeight: "500",
      fontSize: "16px",
    },
    "& .MuiDataGrid-columnHeaderTitle": {
      color: "#fff",
      fontWeight: "500",
      fontSize: "16px",
    },
    "& .Mui-checked": {
      color: "#fc7500",
    },
  },
  button: {
    color: "#fff !important",
    backgroundColor: "#fc7500 !important",
  },
});

let selectedRowsData = [];

export default function Homepage() {
  const classes = useStyles();
  const [rows, setRows] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/Backend/DataLoadingServlet?start=0&end=1000")
      .then((res) => {
        setRows(res.data);
      });

    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/Backend/DataLoadingServlet?start=0&end=1000"
        );
        setRows(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);

  function onRowsSelectionHandler(ids) {
    selectedRowsData = ids.map((id) => rows.find((row) => row.slNo === id));
  }

  return (
    <div style={{ width: "100%" }} className={classes.root}>
      <div style={{ display: "flex", height: "100%" }}>
        <DataGrid
          autoHeight
          rows={rows}
          rowHeight={36}
          getRowId={(rows) => rows.slNo}
          columns={columns}
          initialState={{
            pagination: {
              paginationModel: {
                pageSize: 8,
              },
            },
          }}
          pageSizeOptions={[5, 8, 10, 20, 50, 100]}
          checkboxSelection
          disableSelectionOnClick
          disableColumnFilter
          disableColumnMenu
          onSelectionModelChange={(ids) => onRowsSelectionHandler(ids)}
          components={{ Footer: CustomFooter }}
        />
      </div>
    </div>
  );
}
