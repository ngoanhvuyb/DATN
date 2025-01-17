import { authReducer } from "./Authentication/Reducer";
import { combineReducers, legacy_createStore, applyMiddleware } from "redux";
import { thunk } from "redux-thunk";
import restaurantReducer from "./Restaurant/reducer";
import menuItemReducer from "./menu/Reducer";
import cartReducer from "./Cart/Reducer";
import orderReducer from "./Order/Reducer";
import restaurantsOrderReducer from "./RestaurantOrder/Reducer";
import { ingredientReducer } from "./Ingredients/reducer";

const rooteReducer = combineReducers({
  auth: authReducer,
  restaurant: restaurantReducer,
  menu: menuItemReducer,
  cart: cartReducer,
  order: orderReducer,
  restaurantOrder: restaurantsOrderReducer,
  ingredients: ingredientReducer,
});

export const store = legacy_createStore(rooteReducer, applyMiddleware(thunk));
