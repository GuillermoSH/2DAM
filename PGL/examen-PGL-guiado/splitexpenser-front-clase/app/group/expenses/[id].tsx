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
    const [expense, setExpense] = useState<any>({
        id: "0",
        desc: "prueba",
        amount: "1",
        paid_by: "algo",
    });
    const [desc, onChangeDesc] = useState<string>(expense.desc);
    const [amount, onChangeAmount] = useState<string>(expense.amount);
    const [paidBy, onChangePaidBy] = useState<string>(expense.paid_by);

    const router = useRouter();

    const handleEdit = () => {
        let newExpense = {
                id: expense.id,
                desc: desc,
                amount: amount,
                paid_by: paidBy,
            };-
        setExpense(newExpense);
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
            <View key={expense.id} style={{ padding: 10, borderRadius: 10, boxShadow: "0px 5px 10px #CCC", overflow: "hidden" }}>
                <Text style={{fontWeight: "700"}}>{"Descripcion:"}</Text>
                <TextInput style={{marginBottom: 5}} onChangeText={onChangeDesc} value={desc} />
                <Text style={{fontWeight: "700"}}>{"Cantidad:"}</Text>
                <TextInput onChangeText={onChangeAmount} value={amount} />
                <Text style={{fontWeight: "700"}}>{"Metodo de pago:"}</Text>
                <TextInput onChangeText={onChangePaidBy} value={paidBy} />
                <View style={{ width: "100%", flexDirection: "row", justifyContent: "flex-end", gap: 10 }}>
                    <Button title="Actualizar" onPress={() => handleEdit()} />
                </View>
            </View>

            <Button title="Volver a mis grupos" onPress={() => router.replace("/")} />
        </View>
    );
}
