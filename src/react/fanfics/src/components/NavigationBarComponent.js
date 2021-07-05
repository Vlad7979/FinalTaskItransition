import React from "react"
import { Navbar, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";

const NavigationBarComponent = (props) => {
    <Navbar>
    <Nav className="navbar-right">
        <Link to={"register"} className="nav-link">
          <FontAwesomeIcon icon={faUserPlus} /> Register
        </Link>
        <Link to={"login"} className="nav-link">
          <FontAwesomeIcon icon={faSignInAlt} /> Login
        </Link>
      </Nav>
      </Navbar>
}