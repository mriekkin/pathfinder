%===============================================================================
%
% Settings
%
%===============================================================================
TIMESTAMP = "180831";
PROBLEM_SET = "dao";

% These are derived from the values above
PROBLEM_SET_DIRECTORY = fullfile("../grids", PROBLEM_SET);
RESULT_DATA_DIRECTORY = fullfile("data", TIMESTAMP, PROBLEM_SET);

files = dir(fullfile(RESULT_DATA_DIRECTORY, "*.csv"));
if size(files, 1) == 0
    disp(["No CSV files in the directory " RESULT_DATA_DIRECTORY])
    return;
end

error_per_map = zeros(size(files, 1), 3);

for i = 1:size(files, 1)
    %===========================================================================
    %
    % Load the results data
    %
    %===========================================================================
    filename = fullfile(RESULT_DATA_DIRECTORY, files(i).name);
    A = load("-ascii", filename);
    %disp(["Loaded file " filename])

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

    % The original (un-averaged) columns
    b = A(:, 1);
    dist_D = A(:, 5);
    dist_Astar = A(:, 6);
    dist_JPS = A(:, 7);

    %===========================================================================
    %
    % Load the corresponding scenario file
    %
    %===========================================================================
    [directory, scenario_name, ext] = fileparts(filename);
    filename2 = fullfile(PROBLEM_SET_DIRECTORY, strcat(scenario_name, ".map.scen"));
    S = importdata(filename2, "\t", 1);
    %disp(["Loaded file " filename2])
    
    dist_ref = S.data(:, 9);

    %===========================================================================
    %
    % Compute the error
    %
    %===========================================================================
    error_D = abs(dist_D - dist_ref);
    error_Astar = abs(dist_Astar - dist_ref);
    error_JPS = abs(dist_JPS - dist_ref);

    error_per_map(i, :) = [max(error_D) max(error_Astar) max(error_JPS)];
end

%===========================================================================
%
% Print the results
%
%===========================================================================
error_per_map;

max_error_for_each_algorithm = max(error_per_map)