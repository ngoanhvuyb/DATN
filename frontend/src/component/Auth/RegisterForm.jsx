import {
  Button,
  MenuItem,
  TextField,
  Typography,
} from "@mui/material";
import { Field, Form, Formik } from "formik";
import React from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import * as Yup from "yup"; // Import Yup để xác thực
import { registerUser } from "../State/Authentication/Action";

// Định nghĩa schema xác thực với Yup
const validationSchema = Yup.object().shape({
  fullName: Yup.string().required("Full name is required"),
  email: Yup.string()
    .matches(/^[\w.%+-]+@gmail\.com$/, "Email must end with @gmail.com") // Kiểm tra email phải kết thúc bằng @gmail.com
    .required("Email is required"),
  password: Yup.string().min(6, "Password must be at least 6 characters").required("Password is required"),
  role: Yup.string().required("Role is required"),
});

const initialValues = {
  fullName: "",
  email: "",
  password: "",
  role: "ROLE_CUSTOMER",
};

const RegisterForm = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = (values) => {
    console.log("Form values", values);
    dispatch(registerUser({ userData: values, navigate }));
  };

  return (
    <div>
      <Typography variant="h5" className="text-center">
        Register
      </Typography>

      <Formik
        onSubmit={handleSubmit}
        initialValues={initialValues}
        validationSchema={validationSchema} // Áp dụng schema xác thực
      >
        {({ errors, touched }) => (
          <Form>
            <Field
              as={TextField}
              name="fullName"
              label="Full Name"
              fullWidth
              variant="outlined"
              margin="normal"
              error={touched.fullName && !!errors.fullName}
              helperText={touched.fullName && errors.fullName}
            />
            <Field
              as={TextField}
              name="email"
              label="Email"
              fullWidth
              variant="outlined"
              margin="normal"
              error={touched.email && !!errors.email}
              helperText={touched.email && errors.email}
            />
            <Field
              as={TextField}
              name="password"
              label="Password"
              fullWidth
              variant="outlined"
              margin="normal"
              type="password"
              error={touched.password && !!errors.password}
              helperText={touched.password && errors.password}
            />
            <Field
              as={TextField}
              select
              fullWidth
              margin="normal"
              label="Role"
              name="role"
              error={touched.role && !!errors.role}
              helperText={touched.role && errors.role}
            >
              <MenuItem value={"ROLE_CUSTOMER"}>Customer</MenuItem>
              <MenuItem value={"ROLE_RESTAURANT_OWNER"}>Restaurant Owner</MenuItem>
            </Field>

            <Button
              sx={{ mt: 2, padding: "1rem" }}
              fullWidth
              type="submit"
              variant="contained"
            >
              Register
            </Button>
          </Form>
        )}
      </Formik>

      <Typography variant="body2" align="center" sx={{ mt: 3 }}>
        If you have an account already?
        <Button size="small" onClick={() => navigate("/account/login")}>
          Login
        </Button>
      </Typography>
    </div>
  );
};

export default RegisterForm;
