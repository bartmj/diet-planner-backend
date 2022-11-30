import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";

import { register } from "../slices/auth";
import { clearMessage } from "../slices/message";

const Register = () => {
    const [successful, setSuccessful] = useState(false);

    const { message } = useSelector((state) => state.message);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(clearMessage());
    }, [dispatch]);

    const initialValues = {
        username: "",
        email: "",
        password: ""
    };

    const validationSchema = Yup.object().shape({
        username: Yup.string()
            .test(
                "len",
                "The username must be between 3 and 20 characters.",
                (val) =>
                    val &&
                    val.toString().length >= 3 &&
                    val.toString().length <= 20
            )
            .required("This field is required"),
        email: Yup.string()
            .email("This is not a valid email.")
            .required("This field is required!"),
        password: Yup.string()
            .test(
                "len",
                "The password must be between 6 nad 40 characters.",
                (val) =>
                    val &&
                    val.toString().length >= 6 &&
                    val.toString().length <= 40
            )
            .required("This field is required!")
    });

    const handleRegister = (formValue) => {
        const { username, email, password } = formValue;

        setSuccessful(false);

        dispatch(register({ username, email, password }))
            .unwrap()
            .then(() => {
                setSuccessful(true)
            })
            .catch(() => {
                setSuccessful(false);
        });
    };

    return (
        <div className="container">
            <div>
                <h1>Sign up</h1>
                <Formik
                    initialValues={initialValues}
                    validationSchema={validationSchema}
                    onSubmit={handleRegister}
                >
                    <Form>
                        {!successful && (
                            <div>
                                <div className="form-group">
                                    <label>Username </label>
                                    <br></br>
                                    <Field name="username" type="text" ></Field>
                                    <ErrorMessage name="username" component="div" />
                                </div>
                                <div className="form-group">
                                    <label>Email </label>
                                    <br></br>
                                    <Field name="email" type="text" ></Field>
                                    <ErrorMessage name="email" component="div" />
                                </div>
                                <div className="form-group">
                                    <label>Password </label>
                                    <br></br>
                                    <Field name="password" type="password" ></Field>
                                    <ErrorMessage name="password" component="div" />
                                </div>
                                <div>
                                    <button type="submit">Sign Up</button>
                                </div>
                            </div>
                        )}
                    </Form>
                </Formik>
            </div>

            {message && (
                <div>
                    <div>
                        {message}
                    </div>
                </div>
            )}

        </div>
    );
};

export default Register;








