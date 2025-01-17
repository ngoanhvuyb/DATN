import {
  Card,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import { OrderTable } from "./OrderTable";

const orderStatus = [
  { lable: "Pending", value: "PENDING" },
  { lable: "Completed", value: "COMPLETED" },
  { lable: "All", value: "ALL" },
];

const Orders = () => {
  const [filterValue, setFilterValue] = useState();

  const handleFilter = (e, value) => {
    setFilterValue(value);
  };
  return (
    <div className="px-2">
      <Card className="p-5">
        <Typography sx={{ paddingBottom: "1rem" }} variant="h5">
          Order Status
        </Typography>
        <FormControl>
          <RadioGroup
            onChange={handleFilter}
            row
            name="category"
            value={filterValue || "all"}
          >
            {orderStatus.map((item) => (
              <FormControlLabel
                key={item.lable}
                value={item.value}
                control={<Radio />}
                label={item.lable}
                sx={{ color: "gray" }}
              />
            ))}
          </RadioGroup>
        </FormControl>
      </Card>
      <OrderTable />
    </div>
  );
};

export default Orders;
