import React from "react";
import { Navigate } from 'react-router-dom';
import { useSelector } from "react-redux";

const Profile = () => {
    const { user: currentUser } = useSelector((state => state.auth));

    if (!currentUser) {
        return <Navigate to="/login" />;
    }

    return (
        <div>
            <header>
                <h3>
                    <strong>{currentUser.username}</strong> Profile
                </h3>
            </header>
            <p>
                <strong>Token: </strong> {currentUser.accessToken.substring(0, 20)} ...{" "}
                {currentUser.accessToken.substring(currentUser.accessToken.length - 20)}
            </p>
            <p>
                <strong>Id: </strong> {currentUser.id}
            </p>
            <p>
                <strong>Email: </strong> {currentUser.email}
            </p>
            <p>
                <strong>Authorities: </strong> {currentUser.id}
                <ul>
                    {currentUser.roles && currentUser.roles.map(() => <li key={index}>{role}</li> )}
                </ul>
            </p>
        </div>
    )
}

export default Profile;