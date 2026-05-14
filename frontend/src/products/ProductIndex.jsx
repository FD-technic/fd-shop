import { useEffect, useState } from "react";
import { apiGet } from "../utils/api";
import Table from "../components/Table";

const ProductIndex = () => {
    const [productState, setProducts] = useState([]);
    const columns=[
  { label: "Index", key: "index" },
  { label: "Name", key: "name" },
  { label: "Description", key: "description" },
  { label: "Price", key: "price" }
];

    useEffect(() => {
        apiGet("/api/products")
        .then((data) => setProducts(data.content || data));
    }, []);

    return (
        <>
            <h1>Products</h1>
            <Table label="Počet produktů: " items={productState} columns={columns} />
        </>
    );
}

export default ProductIndex;