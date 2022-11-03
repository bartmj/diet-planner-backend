import './App.css';
import UserPanel from './components/UserPanel';
import { Helmet } from "react-helmet";

function App() {

  return (
    <div className="App">
      <Helmet>
        <title>Diet Planner - Project</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"></meta>
      </Helmet>
      <header className="App-header">
        <div className="container">
          <h1>Diet Planner</h1>
        </div>
      </header>
      <section className="container">
        <UserPanel />
      </section>
    </div>
  );
}

export default App;
