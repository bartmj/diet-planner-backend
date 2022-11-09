import {BrowserRouter, Link} from 'react-router-dom';

export const TopBar = (props) => {

    return  (
        <div className="bg-blue shadow-sm mb-2">
            <div className="container">
                {/*<header className="App-header">*/}
                {/*    <div className="container">*/}
                {/*        <h1>Diet Planner</h1>*/}
                {/*    </div>*/}
                {/*</header>*/}
                <nav className="navbar navbar-light navbar-expand">
                    <div className="grid-1-1">
                        <div className="container">
                            <h1>Diet Planner</h1>
                        </div>
                        <ul className="nav navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link
                                    className="link"
                                    to="/">Home
                                </Link>
                                {/*<Link to="/signup" className="nav-link">*/}
                                {/*    Sign Up*/}
                                {/*</Link>*/}
                            </li>
                            <li className="nav-item">
                                <Link
                                    className="link"
                                    to="/signup">Signup
                                </Link>
                                {/*<Link to="/login" className="nav-link">*/}
                                {/*    Login*/}
                                {/*</Link>*/}
                            </li>
                        </ul>
                    </div>
                    {/*<Link to="/" className="navbar-brand">*/}
                    {/*    <img src={logo} width="60" alt="Hoaxify" /> Hoaxify*/}
                    {/*</Link>*/}
                </nav>
            </div>
        </div>
    )

}
