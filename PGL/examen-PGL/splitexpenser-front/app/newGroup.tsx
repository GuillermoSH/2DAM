import { useRouter } from "expo-router";
import React, { useContext, useEffect, useState } from "react";
import { Button, TextInput, View } from "react-native";
import { AuthContext } from "../context/AuthContext";
import Navbar from "@/components/Navbar";
import Constants from "expo-constants";

const API_URL = Constants.expoConfig?.extra?.apiUrl ?? "";

export default function Groups() {
    const { token, logout } = useContext(AuthContext);
    const router = useRouter();
    const [name, setName] = useState<string | undefined>("");

    useEffect(() => {
        if (!token) {
            setTimeout(() => router.replace("/login"), 0);
        }
    }, [token]);

    const sendNewGroup = async (name: string) => {
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
    };

    const sumbit = async () => {
        const res = await sendNewGroup(name!);
        if (res.ok) {
            router.push("/groups");
        }
    };

    if (!token) return null;

    return (
        <View style={{ flex: 1, justifyContent: "center", alignItems: "center" }}>
            <Navbar logout={logout}></Navbar>
            <TextInput
                placeholder="Nombre del grupo"
                value={name}
                onChangeText={setName}
                style={{ borderWidth: 1, marginBottom: 10, padding: 8 }}
            />
            <Button title="Enviar" onPress={sumbit}></Button>
        </View>
    );
}