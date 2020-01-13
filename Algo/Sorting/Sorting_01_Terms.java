/*
In-Place vs Out-Place:
1) In-Place Sort: Sorting algorithm which does not require any extra place for sorting. e.g. Bubble Sort.
2) Out-Place: Sorting algorithm which does require any extra place for sorting. e.g. Merge Sort.

Stable vs Unstable sorting:
1) Stable Sort: If a sorting algorithm, after sorting the contents, does not change the sequence of similar contents, it 
    is called Stable Sort. 
2) Unstable Sort: If a sorting algorithm, after sorting the contents, does change the sequence of similar contents, it 
    is called Stable Sort. 

Stable sort is important in scenarios where the Sort Key is not the entire identity of the item.
Initial Data:
    [{name: "Sansa", age: "15"}, {name: "Cat", age: "28"}, {name: "Robb", age: "17"}, {name: "Ned", age: "35"}, {name: "Jon", age: "17"}]
Sorted by Stable Sort - Preserves the Order of 'Robb' and 'Jon':
    [{name: "Sansa", age: "15"}, {name: "Robb", age: "17"}, {name: "Jon", age: "17"}, {name: "Cat", age: "28"}, {name: "Ned", age: "35"}]
Sorted by Unstable Sort - Does not preserves the Order of 'Robb' and 'Jon':
    [{name: "Sansa", age: "15"}, {name: "Jon", age: "17"}, {name: "Robb", age: "17"}, {name: "Cat", age: "28"}, {name: "Ned", age: "35"}]

https://www.geeksforgeeks.org/stability-in-sorting-algorithms/
*/
class Sorting_01_Terms {

}