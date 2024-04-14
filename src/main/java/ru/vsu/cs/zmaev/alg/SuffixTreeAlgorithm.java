package ru.vsu.cs.zmaev.alg;

import java.util.ArrayList;
import java.util.List;

public class SuffixTreeAlgorithm {

    // Сложность алгоритма - O(n)
    private static class Node {
        String substring = "";
        List<Integer> children = new ArrayList<>();
    }

    private static class SuffixTree {
        private final List<Node> nodes = new ArrayList<>();

        public SuffixTree(String str) {
            nodes.add(new Node());
            for (int i = 0; i < str.length(); ++i) {
                addSuffix(str.substring(i));
            }
            this.visualize();
        }

        private void addSuffix(String suffix) {
            int node = 0; // индекс текущего узла, начинаем с корня дерева
            int i = 0; // индекс текущего символа в суффиксе
            while (i < suffix.length()) {
                char c = suffix.charAt(i); // текущий символ суффикса
                List<Integer> children = nodes.get(node).children; // список детей текущего узла
                int indexCh = 0; // индекс текущего ребенка
                int child; // индекс нового узла (ребенка)

                // Цикл по списку детей, чтобы найти совпадающего ребенка (если таковой есть)
                while (true) {
                    // Если все дети текущего узла (т.е. ребенка с таким символом не существует)
                    if (indexCh == children.size()) {
                        child = nodes.size(); // индекс нового узла

                        // Новый узел подстроки суффикса
                        Node temp = new Node();
                        temp.substring = suffix.substring(i);
                        nodes.add(temp);

                        children.add(child); // добавление индекса нового узла в список детей текущего узла
                        return;
                    }
                    child = children.get(indexCh); // индекс следующего ребенка

                    // Если ребенок с таким символом существует, выходим из цикла (переходим к этому ребенку)
                    if (nodes.get(child).substring.charAt(0) == c) break;

                    indexCh++; // переходим к следующему ребенку
                }

                // Поиск префикса, общего с текущим ребенком
                String substringChild = nodes.get(child).substring; // подстрока текущего ребенка
                int j = 0; // индекс сравниваемого символа

                // Обход подстроки ребенка
                while (j < substringChild.length()) {
                    // Если есть различие в символах между суффиксом и подстрокой ребенка
                    if (suffix.charAt(i + j) != substringChild.charAt(j)) {
                        // Разделение ребенка на два узла
                        int oldChild = child; // запоминаем индекс текущего ребенка
                        child = nodes.size(); // индекс нового узла

                        // Новый узел с подстрокой общего префикса
                        Node temp = new Node();
                        temp.substring = substringChild.substring(0, j);
                        temp.children.add(oldChild); // добавляем старый узел как ребенка
                        nodes.add(temp);

                        //Обновляем дерево
                        nodes.get(oldChild).substring = substringChild.substring(j); // обновляем подстроку старого узла (оставшаяся часть подстроки ребенка)
                        nodes.get(node).children.set(indexCh, child); // заменяем старого ребенка на новый узел
                        break;
                    }
                    j++; // переход к следующему символу
                }
                i += j;  // переход вперед на количество совпавших символов
                node = child;  // переход к следующему узлу
            }
        }

        public void visualize() {
            if (nodes.isEmpty()) {
                System.out.println();
                return;
            }
            visualize_f(0, "");
        }

        private void visualize_f(int n, String pre) {
            List<Integer> children = nodes.get(n).children;
            if (children.isEmpty()) {
                System.out.println(" └" + nodes.get(n).substring);
                return;
            }
            System.out.println(" └" + nodes.get(n).substring);
            for (int i = 0; i < children.size() - 1; i++) {
                Integer c = children.get(i);
                System.out.print(pre + " ┆");
                visualize_f(c, pre + " ┆");
            }
            System.out.print(pre + " ┆");
            visualize_f(children.get(children.size() - 1), pre + "  ");
        }
    }

    public static void main(String[] args) {
//        SuffixTree suffixTree = new SuffixTree("abcabxabcd");
        SuffixTree suffixTree = new SuffixTree("banana$");
//        SuffixTree suffixTree = new SuffixTree("abracadabra$");
    }
}