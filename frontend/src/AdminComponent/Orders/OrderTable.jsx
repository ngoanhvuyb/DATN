import {
  Avatar,
  AvatarGroup,
  Box,
  Button,
  Card,
  CardHeader,
  Chip,
  Menu,
  MenuItem,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchRestaurantsOrder,
  updateOrderStatus,
} from "../../component/State/RestaurantOrder/Action";

const orderStatus = [
  { label: "Pending", value: "PENDING" },
  { label: "Complete", value: "COMPLETE" },
  { label: "Out For Delivery", value: "OUT_FOR_DELIVERY" },
  { label: "Delivery", value: "DELIVERY" },
];

export const OrderTable = () => {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { restaurant, restaurantOrder } = useSelector((store) => store);
  const [anchorEl, setAnchorEl] = useState(null);
  const [selectedOrderId, setSelectedOrderId] = useState(null);

  const open = Boolean(anchorEl);
  const handleClick = (event, orderId) => {
    setAnchorEl(event.currentTarget);
    setSelectedOrderId(orderId);
  };
  const handleClose = () => {
    setAnchorEl(null);
    setSelectedOrderId(null);
  };

  useEffect(() => {
    dispatch(
      fetchRestaurantsOrder({
        jwt,
        restaurantId: restaurant.usersRestaurant?.id,
      })
    );
  }, [dispatch, jwt, restaurant.usersRestaurant?.id]);

  const handleUpdateOrder = (orderId, orderStatus) => {
    dispatch(updateOrderStatus({ orderId, orderStatus, jwt }))
      .then(() => {
        alert("Order updated successfully!");
      })
      .catch((error) => {
        alert("Failed to update order: " + error.message);
      });
    handleClose();
  };

  return (
    <Box>
      <Card className="mt-1">
        <CardHeader title={"All Orders"} sx={{ pt: 2, alignItems: "center" }} />
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell align="right">Image</TableCell>
                <TableCell align="right">Customer</TableCell>
                <TableCell align="right">Price</TableCell>
                <TableCell align="right">Name</TableCell>
                <TableCell align="right">Ingredients</TableCell>
                <TableCell align="right">Status</TableCell>
                <TableCell align="right">Update</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {restaurantOrder.orders.map((item) => (
                <TableRow
                  key={item.id} // Use a unique key for each row
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell component="th" scope="row">
                    {item.id}
                  </TableCell>
                  <TableCell align="right">
                    <AvatarGroup>
                      {item.items.map((orderItem, index) => (
                        orderItem.food?.images?.[0] ? (
                          <Avatar key={index} src={orderItem.food.images[0]} />
                        ) : (
                          <Avatar key={index}>{orderItem.food?.name[0]}</Avatar>
                        )
                      ))}
                    </AvatarGroup>
                  </TableCell>
                  <TableCell align="right">{item.customer?.fullName}</TableCell>
                  <TableCell align="right">${item.totalAmount}</TableCell>
                  <TableCell align="right">
                    {item.items.map((orderItem, index) => (
                      <p key={index}>{orderItem.food?.name}</p>
                    ))}
                  </TableCell>
                  <TableCell align="right">
                    {item.items.map((orderItem, index) => (
                      <div key={index}>
                        {orderItem.ingredients.map((ingredient, idx) => (
                          <Chip key={idx} label={ingredient} />
                        ))}
                      </div>
                    ))}
                  </TableCell>
                  <TableCell align="right">{item.orderStatus}</TableCell>
                  <TableCell align="right">
                    <Button
                      id="basic-button"
                      aria-controls={open ? "basic-menu" : undefined}
                      aria-haspopup="true"
                      aria-expanded={open ? "true" : undefined}
                      onClick={(e) => handleClick(e, item.id)}
                      sx={{
                        color: 'yellow' ,                  
                      }}
                    >
                      Update
                    </Button>
                    {selectedOrderId === item.id && (
                      <Menu
                        id="basic-menu"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                        MenuListProps={{
                          "aria-labelledby": "basic-button",
                        }}
                      >
                        {orderStatus.map((status) => (
                          <MenuItem
                            key={status.value}
                            onClick={() =>
                              handleUpdateOrder(item.id, status.value)
                            }
                          >
                            {status.label}
                          </MenuItem>
                        ))}
                      </Menu>
                    )}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Card>
    </Box>
  );
};
