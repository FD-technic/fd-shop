import { Link, Route, Routes } from "react-router-dom";
import ProductIndex from "./products/ProductIndex";

const Shop = () => {
  return (
    <div>
      <header>
        <h1>FD-Shop</h1>
        <nav>
          <ul>
            <li>
              <Link to={"/"}>Home</Link>
            </li>
            <li>
              <Link to={"/shop"}>Shop</Link>
            </li>
            <li>
              <Link to={"products"}>Products</Link>
            </li>
          </ul>
        </nav>
      </header>

      <Routes>
        <Route index element={<h2>Welcome to FD-Shop</h2>} />
        <Route path={"products"} element={<ProductIndex />} />
      </Routes>
    </div>
  );
};

export default Shop;
