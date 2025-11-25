import { Link } from "expo-router";
import { View, Text } from "react-native";

type GroupCardProps = {
    id: string,
    name: string,
}

export default function GroupCard({ id, name }: GroupCardProps) {
    return (
        <View style={{ width: 150, height: 150, backgroundColor: "#FEFEFE", position: "relative", borderRadius: 15, boxShadow: "2px 2px 10px #EEE", justifyContent: "center", alignItems: "center" }}>
            <Text>{name}</Text>
            <Link style={{position: "absolute", bottom: 20, backgroundColor: ""}} href={"/groups/" + id}><Text>Ver detalles</Text></Link>
        </View>
    );
}