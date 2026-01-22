import { useRouter } from "expo-router";
import React, { useContext, useEffect, useState } from "react";
import { Button, Text, View } from "react-native";
import { AuthContext } from "../context/AuthContext";
import Navbar from "@/components/Navbar";
import Constants from "expo-constants";
import GroupCard from "@/components/GroupCard";

const API_URL = Constants.expoConfig?.extra?.apiUrl ?? "";
type Group = {
    id: string,
    name: string,
}

export default function Groups() {
    const { token, logout } = useContext(AuthContext);
    const router = useRouter();
    const [groups, setGroups] = useState<Group[]>([]);

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
        } finally {

        }
    };

    useEffect(() => {
        getGroups();
        console.log(groups);
    }, []);

    useEffect(() => {
        if (!token) {
            setTimeout(() => router.replace("/login"), 0);
        }
    }, [token]);

    if (!token) return null;

    return (
        <View style={{ position: "relative", flex: 1, justifyContent: "center", alignItems: "center" }}>
            <Navbar logout={logout}></Navbar>
            <Button title="Crear grupo" onPress={() => router.push("/newGroup")} />
            <View style={{display: "flex", flexWrap: "wrap", flexDirection: "row", gap: 20, alignItems: "center", justifyContent: "center"}}>
            {groups.map((group) => (
                <GroupCard key={group.id} id={group.id} name={group.name} />
            ))}
            </View>

        </View>
    );
}