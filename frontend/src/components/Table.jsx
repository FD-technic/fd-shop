const Table = ({ label, columns, items }) => {
  return (
    <>
      <p>
        {label} {items.length}
      </p>
      <table>
        <thead>
          <tr>
            {columns.map((column) => (
              <th key={column.key}>{column.label}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {items.map((item, index) => (
            <tr key={item._id}>
              {columns.map((column) => (
              <td key={column.key}>
                {column.key === "index"
                    ? index + 1
                    : item[column.key]}
              </td>
            ))}
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

export default Table;
