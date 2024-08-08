import React from "react";
import Header from "./components/header/Header";
import Body from "./components/body/Body";
import Footer from "./components/footer/Footer";

export default function App() {
  const scrollbarStyle = {
    width: "12px",
  };

  const scrollbarTrackStyle = {
    backgroundColor: "#666666",
  };

  const scrollbarThumbStyle = {
    backgroundColor: "#505050",
    borderRadius: "100px",
  };

  return (
    <>
      <style>
        {`
          ::-webkit-scrollbar {
            width: ${scrollbarStyle.width}
          }

          ::-webkit-scrollbar-track {
            background-color: ${scrollbarTrackStyle.backgroundColor}
          }

          ::-webkit-scrollbar-thumb {
            background-color: ${scrollbarThumbStyle.backgroundColor},
            border-radius: ${scrollbarThumbStyle.borderRadius}
          }
        `}
      </style>
      <Header />
      <Body />
      <Footer />
    </>
  );
}
