import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import javaLogo from "./assets/Java.svg"
import springLogo from "./assets/Spring.svg"
import PotgresLogo from "./assets/PostgresSQL.svg"
import { Link } from "react-router-dom";

import "./App.css";
import "./shop.css";

function Home() {
  return (
    <>
      
      <section id="center">
        <div>
          <h1>FD-shop</h1>
          <p>
            <strong>Long-term fullstack e-commerce project.</strong>
          </p>
          <p>
            <span>A study project that will grow over time.</span>
          </p>
        </div>
      </section>
      <section>
        <h2>Frontend</h2>
        
        <h3><img src={reactLogo} width="50" className="framework" alt="React logo" /> React</h3>
        
        <h3><img src={viteLogo} width="50" className="vite" alt="Vite logo" /> Vite</h3>
      </section>
      <section>
        <h2>Backend</h2>
        
        <h3><img src={javaLogo} width="50" className="java" alt="Java logo" /> Java</h3>
        
        <h3><img src={springLogo} width="50" className="spring" alt="Spring logo" /> Spring</h3>
        
        <h3><img src={PotgresLogo} width="50" className="PotgresSQL" alt="PotgresSQL logo" /> PostgreSQL</h3>
      </section>
      <hr />
      <section>
        <Link to={"/shop"}
        className="btn">Enter Shop</Link>
      </section>
    </>
  );
}

export default Home;
