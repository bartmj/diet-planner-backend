import React, {useEffect} from "react";
import { Navigate } from 'react-router-dom';
import { useSelector } from "react-redux";

const Profile = () => {
    const { user: currentUser } = useSelector((state => state.auth));

    if (!currentUser) {
        return <Navigate to="/login" />;
    }

    return (
        <div className="container">
            <h1>Profile</h1>
            <header>
                <h3>
                    <strong>User: </strong> {currentUser.username}
                </h3>
            </header>
            <p>
                <strong>Token: </strong> {currentUser.token.substring(0, 20)}
            </p>
            <p>
                <strong>Id: </strong> {currentUser.id}
            </p>
            <p>
                <strong>Email: </strong> {currentUser.email}
            </p>
            <div>
                <strong>Authorities: </strong>
                <ul>
                    {currentUser.roles &&
                    currentUser.roles.map((role, index) => <li key={index}>{role}</li> )}
                </ul>
            </div>
        </div>
    )
}

export default Profile;