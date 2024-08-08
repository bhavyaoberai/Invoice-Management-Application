import React from "react";

export default function Footer() {
  const footerStyle = {
    height: "50px",
    fontSize: "14px",
    fontFamily: "Roboto",
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-around",
    textAlign: "center",
    borderBottom: "1px solid #a9a9a9",
  };

  return (
    <div style={footerStyle}>
      <span>
        Privacy Policy | @ 2023 HighRadius Corporation. All rights reserved
      </span>
    </div>
  );
}
