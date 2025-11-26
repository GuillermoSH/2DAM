import { useRouter } from "expo-router";
import React, { useContext, useEffect, useState } from "react";
import { Button, Text, TextInput, View, Alert } from "react-native";
import { AuthContext } from "../context/AuthContext";

type Expense = {
  id: string;
  desc: string;
  amount: string;
  paid_by: string;
}

export default function GroupDetails() {
  const { token } = useContext(AuthContext);
  const [expenses, setExpenses] = useState<Expense[]>([
    {
      id: "1",
      desc: "Producto 1",
      amount: "2",
      paid_by: "Tarjeta",
    },
    {
      id: "2",
      desc: "Producto 2",
      amount: "1",
      paid_by: "Efectivo",
    },
  ]);
  const router = useRouter();

  const handleDelete = (id: string) => {
    expenses.forEach((expense: Expense) => {
      if (expense.id === id) {
        console.log("Borrando id: " + id);
        expenses.splice(expenses.indexOf(expense), 1);
        setExpenses([...expenses]);
      }
    });
  };

  const handleEdit = (id: string) => {
    console.log("Editando id: " + id);
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
      {expenses.map((e) => (
        <View key={e.id} style={{ gap: 5, padding: 10, borderRadius: 10, boxShadow: "0px 5px 10px #CCC", overflow: "hidden" }}>
          <Text>{`${e.desc} => ${e.amount}, pagado con ${e.paid_by.toLowerCase()}`}</Text>
          <View style={{ width: "100%", flexDirection: "row", justifyContent: "flex-end", gap: 10 }}>
            <Button title="Editar" onPress={() => handleEdit(e.id)} />
            <Button title="Borrar" onPress={() => handleDelete(e.id)} />
          </View>
        </View>
      ))}

      <Button title="Volver a mis grupos" onPress={() => router.replace("/")} />
    </View>
  );
}
