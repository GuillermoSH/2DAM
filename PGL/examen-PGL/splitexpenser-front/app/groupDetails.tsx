import Navbar from "@/components/Navbar";
import { AuthContext } from "@/context/AuthContext";
import { Link } from "expo-router";
import { useContext } from "react";
import { View, Text } from "react-native";

type GroupDetailsProps = {
    id: string,
    name: string,
}

export default function GroupDetails({ id, name }: GroupDetailsProps) {
    const { logout } = useContext(AuthContext);

    return (
        <View style={{ flex: 1, display: "flex", justifyContent: "center", alignItems: "center", height: "100%", width: "100%" }}>
            <Navbar logout={logout}></Navbar>
            <Text style={{fontSize: 40}}>{`Grupo ${id}: ${name}`}</Text>
        </View>
    );
}