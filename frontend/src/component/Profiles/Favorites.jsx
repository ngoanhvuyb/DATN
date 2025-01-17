import React from "react";
import RestaurantCard from "../Restaurant/RestaurantCard";
import { useSelector } from "react-redux";

const Favorites = () => {
  const { auth } = useSelector((store) => store);
  console.log("Favorites Data:", auth.favorites); // Thêm dòng này để kiểm tra dữ liệu
  return (
    <div>
      <h1 className="py-5 text-xl font-semibold text-center">My Favorites</h1>
      
      <div className="flex flex-wrap gap-3 justify-center">
        {auth.favorites.map((item) => (
          <RestaurantCard
            key={item.id} // Thêm thuộc tính key với giá trị duy nhất
            item={item}
          />
        ))}
      </div>
    </div>
  );
};

export default Favorites;
