import { useRouter } from "expo-router";
import React, { useContext, useEffect, useState } from "react";
import { Button, Text, TextInput, View } from "react-native";
import { AuthContext } from "../context/AuthContext";

type Group = {
  id: string;
  name: string;
}

export default function Home() {
  const { token, logout } = useContext(AuthContext);
  const router = useRouter();
  const [groups, setGroups] = useState<Group[]>([
    {
      id: "1",
      name: "Grupo 1",
    },
    {
      id: "2",
      name: "Grupo 2",
    },
  ]);

  useEffect(() => {
    if (!token) {
      setTimeout(() => router.replace("/login"), 0);
    }
  }, [token]);

  if (!token) return null;

  return (
    <View style={{ flex: 1, justifyContent: "center", gap: 10, alignItems: "center" }}>
      <Text>Sus Grupos</Text>
      <TextInput placeholder="Agregue su grupo"></TextInput>
      <Button title="Registrar"></Button>
      {groups.map((group) => (
        <View key={group.id}>
          <Text onPress={() => router.replace("/group/" + group.id)}>{group.name}</Text>
        </View>
      ))}
      <Button title="Eliminar cuenta" onPress={() => router.push("/unregister")} />
      <Button title="Cerrar sesión" onPress={() => void logout()} />
    </View>
  );
}
