import { useGlobalSearchParams, useLocalSearchParams, useRouter } from "expo-router";
import React, { useContext, useEffect, useState } from "react";
import { Button, Text, TextInput, View, Alert } from "react-native";
import { AuthContext } from "@/context/AuthContext";

type Expense = {
    id: string;
    desc: string;
    amount: string;
    paid_by: string;
}

export default function ExpensesEdit() {
    const { token } = useContext(AuthContext);
    const [expense, setExpense] = useState<Expense>({
        id: "0",
        desc: "prueba",
        amount: "1",
        paid_by: "algo",
    });
    const router = useRouter();

    const handleEdit = () => {
        console.log("Editando " + expense.desc);
    };

    useEffect(() => {
        if (!token) {
            setTimeout(() => router.replace("/login"), 0);
        }
    }, [token]);

    if (!token) return null;

    return (
        <View style={{ flex: 1, justifyContent: "center", gap: 20, padding: 20 }}>
            <Text style={{ fontSize: 20, fontWeight: "bold" }}>Mi grupo</Text>
            <View key={expense.id} style={{ gap: 5, padding: 10, borderRadius: 10, boxShadow: "0px 5px 10px #CCC", overflow: "hidden" }}>
                <TextInput>{expense.desc}</TextInput>
                <TextInput>{expense.amount}</TextInput>
                <TextInput>{expense.paid_by}</TextInput>
                <View style={{ width: "100%", flexDirection: "row", justifyContent: "flex-end", gap: 10 }}>
                    <Button title="Actualizar" onPress={() => handleEdit} />
                </View>
            </View>

            <Button title="Volver a mis grupos" onPress={() => router.replace("/")} />
        </View>
    );
}
