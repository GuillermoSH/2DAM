import { Link, router } from "expo-router";
import { View, Text, Button } from "react-native";

type NavbarProps = {
    logout: () => void;
}

export default function Navbar({ logout }: NavbarProps) {
    return (
        <View style={{ position: "absolute", top: 0, flex: 1, display: "flex", flexDirection: "row", gap: 20, height: 50, width: "100%", justifyContent: "center", alignItems: "center", borderBottomWidth: 2, borderBottomColor: "#AAA" }}>
            <Link href={"/"}><Text>Inicio</Text></Link>
            <Link href={"/groups"}><Text>Grupos</Text></Link>
            <Button title="Logout" onPress={() => void logout()} />
        </View>
    );
}