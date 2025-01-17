// export const isPresentInFavorites = (favorites, restaurant) => {
//   for (let item of favorites) {
//     if (restaurant.id === item.id) {
//       return true;
//     }
//   }
//   return false;
// };
export const isPresentInFavorites = (favorite, restaurant) => {
  if (!Array.isArray(favorite)) {
    return false; // Nếu favorite không phải là mảng, trả về false
  }

  for (let item of favorite) {
    if (restaurant.id === item.id) {
      return true;
    }
  }

  return favorite.some((item) => item.id === restaurant.id);
};
