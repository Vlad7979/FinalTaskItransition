import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import NavigationBarComponent from "./components/NavigationBarComponent";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";


const App = () => {
  return (
    <Router>
      <NavigationBarComponent />
    </Router>
  );
}

export default App;
