import React from "react";
import styles from "./Table.module.css";

export default function Table({ columns, data }) {
    return (
        <div className={styles.tableWrapper}>
            <table className={styles.table}>
                <thead>
                <tr>
                    {columns.map((column, index) => (
                        <th key={index} className={styles.th}>
                            {column.Header}
                        </th>
                    ))}
                </tr>
                </thead>
                <tbody>
                {data.length > 0 ? (
                    data.map((row, rowIndex) => (
                        <tr key={rowIndex} className={styles.tr}>
                            {columns.map((column, colIndex) => (
                                <td key={colIndex} className={styles.td}>
                                    {row[column.accessor]}
                                </td>
                            ))}
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan={columns.length} className={styles.noData}>
                            데이터가 없습니다.
                        </td>
                    </tr>
                )}
                </tbody>
            </table>
        </div>
    );
}
