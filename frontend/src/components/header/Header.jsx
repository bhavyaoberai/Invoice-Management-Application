import React from "react";
import abclogo from "../../assets/abclogo.svg";
import hrclogo from "../../assets/hrclogo.svg";

export default function Header() {
  const headerStyle = {
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-between",
    height: "90px",
    padding: "12px 8px 4px 8px",
  };

  const logoStyle = {
    position: "relative",
  };

  const abcLogoStyle = {
    width: "240px",
  };

  const hrcLogoStyle = {
    width: "160px",
    position: "absolute",
    left: "50%",
    transform: "translate(-50%)",
  };

  const invoiceHeadingStyle = {
    color: "rgb(247, 52, 52)",
    fontFamily: "Roboto, sans-serif",
    fontSize: "24px",
    fontWeight: "600",
  };

  return (
    <div style={headerStyle}>
      <div style={logoStyle}>
        <img src={abclogo} alt="abclogo" style={abcLogoStyle} />
        <img src={hrclogo} alt="hrclogo" style={hrcLogoStyle} />
      </div>
      <span style={invoiceHeadingStyle}>Invoice List</span>
    </div>
  );
}
