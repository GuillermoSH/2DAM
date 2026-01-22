import { useRouter } from "expo-router";
import React, { useContext, useEffect } from "react";
import { Button, Text, View } from "react-native";
import { AuthContext } from "../context/AuthContext";
import Navbar from "@/components/Navbar";

export default function Home() {
  const { token, logout } = useContext(AuthContext);
  const router = useRouter();

  useEffect(() => {
    if (!token) {
      setTimeout(() => router.replace("/login"), 0);
    }
  }, [token]);

  if (!token) return null;

  return (
    <View style={{ flex: 1, position: "relative", justifyContent: "center", alignItems: "center" }}>
      <Navbar logout={logout}></Navbar>
      <Text>Bienvenido a Home!</Text>

      <Button title="Cerrar sesión" onPress={() => void logout()} />
    </View>
  );
}
