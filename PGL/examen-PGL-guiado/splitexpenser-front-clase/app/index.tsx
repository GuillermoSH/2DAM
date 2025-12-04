import { useRouter } from "expo-router";
import React, { useContext, useEffect, useState } from "react";
import { Button, Text, TextInput, View } from "react-native";
import { AuthContext } from "../context/AuthContext";
import Constants from "expo-constants";

const API_URL = Constants.expoConfig?.extra?.apiUrl ?? "";
type Group = {
  id: string;
  name: string;
}

export default function Home() {
  const { token, logout } = useContext(AuthContext);
  const router = useRouter();
  const [groups, setGroups] = useState<Group[]>([]);
  const [name, setName] = useState<string>("");

  const getGroups = async () => {
    try {
      const res = await fetch(`${API_URL}/groups`, {
        method: "GET",
        headers: {
          "accept": "application/json",
          "Authorization": "Bearer " + token,
        },
      });
      const data = await res.json() as Group[];
      setGroups(data);
    } catch (err) {
      console.log(err);
    }
  };

  const handleGroupAdd = async (name: string) => {
    try {
      const res = await fetch(`${API_URL}/groups`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " + token,
        },
        body: JSON.stringify({ name }),
      });
      return await res.json();
    } catch (err) {
      return { ok: false, msg: "Network error" };
    }
  }

  useEffect(() => {
    getGroups();
  }, []);

  useEffect(() => {
    if (!token) {
      setTimeout(() => router.replace("/login"), 0);
    }
  }, [token]);

  const submit = async () => {
    await handleGroupAdd(name);
    getGroups();
  };

  if (!token) return null;

  return (
    <View style={{ flex: 1, justifyContent: "center", gap: 10, alignItems: "center" }}>
      <Text>Sus Grupos</Text>
      <TextInput
        placeholder="Agregue su grupo"
        value={name}
        onChangeText={setName}>
      </TextInput>
      <Button title="Registrar" onPress={submit}></Button>
      {groups.map((group) => (
        <View key={group.id}>
          <Text onPress={() => router.replace("/group/" + group.id)}>{group.name}</Text>
        </View>
      ))}
      <Button title="Eliminar cuenta" onPress={() => router.push("/unregister")} />
      <Button title="Cerrar sesión" onPress={logout} />
    </View>
  );
}
