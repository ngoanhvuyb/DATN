import React from "react";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { Button } from "@mui/material";
import { useDispatch, useSelector } from "react-redux"; // Import useSelector để lấy dữ liệu từ store
import { useNavigate } from "react-router-dom";
import { logout } from "../State/Authentication/Action";

const UserProfile = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { auth } = useSelector((store) => store); // Lấy thông tin người dùng từ Redux store

  const handleLogout = () => {
    dispatch(logout());// Xử lý logic đăng xuất
    navigate("/");
  };

  // Lấy tên và email từ auth.user, cung cấp giá trị mặc định nếu không có
  const user = auth?.user || { fullName: "Guest", email: "Not available" };

  return (
    <div className="min-h-[80vh] flex flex-col justify-center items-center text-center">
      <div className="flex flex-col items-center justify-center">
        <AccountCircleIcon sx={{ fontSize: "9rem" }} />
        <h1 className="py-5 text-2xl font-semibold">{user.fullName}</h1> {/* Hiển thị tên người dùng */}
        <p>Email: {user.email}</p> {/* Hiển thị email người dùng */}
        <Button
          variant="contained"
          onClick={handleLogout}
          sx={{ margin: "2rem 0rem" }}
        >
          Logout
        </Button>
      </div>
    </div>
  );
};

export default UserProfile;
