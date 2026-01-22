import { Slot, Stack } from "expo-router";
import React from "react";
import { AuthProvider } from "../context/AuthContext";
import { SafeAreaView } from "react-native-safe-area-context";

export default function RootLayout() {
  return (
    <AuthProvider>
      <SafeAreaView style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
        <Slot />
      </SafeAreaView>
    </AuthProvider>
  );
}
