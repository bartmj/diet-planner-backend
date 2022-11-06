import './App.css';
import UserPanel from './components/UserPanel';

function App() {

  return (
    <div className="App">
        <title>Diet Planner - Project</title>
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
