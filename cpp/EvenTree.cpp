// Problem: https://www.hackerrank.com/challenges/even-tree

#include <iostream>
#include <vector>

namespace {

struct edge_t {
    int to;
    int from;
};

struct node_data_t {
    int cuts;
    int childCount;
};

node_data_t even_nodes_impl(int nodeIdx, std::vector<edge_t> const& edges) {  //
    node_data_t data{};
    for (auto const& edge : edges) {

        // Filter children of nodeIdx
        if (edge.from == nodeIdx) {
            auto const childData = even_nodes_impl(edge.to, edges);

            auto const childTreeNodeCount = childData.childCount + 1;
            data.childCount += childTreeNodeCount;

            auto const canCut = childTreeNodeCount % 2 == 0;
            data.cuts += childData.cuts + canCut;
        }
    }

    return data;
}

// Note: this algorithm only works if the tree has an even number of nodes.
int even_nodes(int nodeCount, std::vector<edge_t> const& edges) {  //
    // If you are a good person, uncomment this:
    // if (nodeCount % 2) return 0;

    auto const rootData = even_nodes_impl(1, edges);
    return rootData.cuts;
}
}  // namespace

int main() {
    int const nodeCount{10};
    std::vector<edge_t> const edges{{2, 1}, {3, 1}, {4, 3}, {5, 2}, {6, 1},
                                    {7, 2}, {8, 6}, {9, 8}, {10, 8}};
    return even_nodes(nodeCount, edges);
}
