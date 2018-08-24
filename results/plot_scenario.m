FILE = "data/180821/dao/orz900d.csv";
FIGURE = 3;
SAVE_FILENAME = "";
Y_LIMITS = [];

% Load the file
A = load("-ascii", FILE);

% Check the number of rows and columns
rows = size(A, 1);
cols = size(A, 2);
if rows == 0
    disp(["File has 0 rows: ", filename])
end
if cols != 7
    disp(["File should have exactly 7 columns: " filename " (has " num2str(cols) ")"])
    break;
end

% Average over buckets
buckets = ceil(rows / 10);
B = zeros(buckets, 7);
for k = 1:buckets
    bucket_start = 1 + 10 * (k-1);
    bucket_end = min(rows, bucket_start + 9);
    bucket_data = A(bucket_start:bucket_end, :);
    B(k, :) = mean(bucket_data);
end

% Clear figures
clf(figure(FIGURE));

% Plot the graph
plot_result(FIGURE, B, SAVE_FILENAME, Y_LIMITS);
